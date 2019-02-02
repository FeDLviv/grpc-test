package net.omisoft.grpc;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Request received from client:" + request);

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(greeting)
                .addPhone(101)
                .addPhone(102)
                .addPhone(103)
                .addPhone(104)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}