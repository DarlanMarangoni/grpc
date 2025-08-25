package com.darlanmarangoni.grpcclient;

import com.darlanmarangoni.grpc.proto.HelloReply;
import com.darlanmarangoni.grpc.proto.HelloRequest;
import com.darlanmarangoni.grpc.proto.SimpleGrpc;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;

@Configuration
public class HelloService {

    private final SimpleGrpc.SimpleBlockingStub stub;

    public HelloService(SimpleGrpc.SimpleBlockingStub stub) {
        this.stub = stub;
    }

    @PostConstruct
    public void streamHello() {
        Iterator<HelloReply> streamHello = stub.streamHello(HelloRequest.newBuilder()
                .setName("Darlan")
                .build());
        streamHello.forEachRemaining(System.out::println);
    }

    @PostConstruct
    public void sayHello() {
        HelloReply sayHello = stub.sayHello(HelloRequest.newBuilder()
                .setName("Darlan")
                .build());
        System.out.println(sayHello.getMessage());
    }
}
