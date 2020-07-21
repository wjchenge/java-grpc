package demo.server.impl;

import demo.server.DemoReply;
import demo.server.DemoRequest;
import demo.server.DemoServiceGrpc;
import io.grpc.stub.StreamObserver;

public class DemoServerImpl extends DemoServiceGrpc.DemoServiceImplBase {

    @Override
    public void sayHello(DemoRequest request, StreamObserver<DemoReply> responseObserver) {
        DemoReply reply = DemoReply.newBuilder().setMessage("java--Hello" + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
