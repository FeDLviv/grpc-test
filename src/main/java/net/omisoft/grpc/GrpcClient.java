package net.omisoft.grpc;

public class GrpcClient {

//    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder
//                .forAddress("localhost", 8080)
//                .usePlaintext()
//                .build();
//
//        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
//
//        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
//                .setFirstName("John")
//                .setLastName("Вщу")
//                .build());
//
//        System.out.println(helloResponse.getMessage());
//        helloResponse.getPhoneList().forEach(System.out::println);
//
//        channel.shutdown();
//    }

}