/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.client;

import demo.server.DemoReply;
import demo.server.DemoRequest;
import demo.server.DemoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class DemoClient {

  public static void main(String[] args) throws Exception {
    String name = "world!!!!!!!!!!!!!!!!!!!!!!!";
    String target = "localhost:50051";
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
        .usePlaintext()
        .build();
    DemoServiceGrpc.DemoServiceBlockingStub blockingStub = DemoServiceGrpc.newBlockingStub(channel);
    DemoRequest request = DemoRequest.newBuilder().setName(name).build();
    try {
      DemoReply reply = blockingStub.sayHello(request);
      System.out.println("返回内容 === " + reply.getMessage());
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
