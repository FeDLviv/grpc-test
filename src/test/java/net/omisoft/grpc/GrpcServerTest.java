package net.omisoft.grpc;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GrpcServerTest {

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Test
    public void greeterImpl_replyMessage() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(new HelloServiceImpl())
                .build()
                .start()
        );

        // Create a client channel and register for automatic graceful shutdown.
        HelloServiceGrpc.HelloServiceBlockingStub blockingStub = HelloServiceGrpc
                .newBlockingStub(grpcCleanup
                        .register(InProcessChannelBuilder
                                .forName(serverName)
                                .directExecutor()
                                .build()
                        )
                );

        HelloResponse response = blockingStub.hello(HelloRequest.newBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .build()
        );

        Assert.assertEquals("Hello, John Doe", response.getMessage());
        Assert.assertEquals(4, response.getPhoneCount());
        Assert.assertTrue(response.getPhoneList().contains(101));
    }

}