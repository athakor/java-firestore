/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.firestore.v1;

import static com.google.cloud.firestore.v1.FirestoreClient.ListCollectionIdsPagedResponse;
import static com.google.cloud.firestore.v1.FirestoreClient.ListDocumentsPagedResponse;
import static com.google.cloud.firestore.v1.FirestoreClient.PartitionQueryPagedResponse;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.testing.LocalChannelProvider;
import com.google.api.gax.grpc.testing.MockGrpcService;
import com.google.api.gax.grpc.testing.MockServiceHelper;
import com.google.api.gax.grpc.testing.MockStreamObserver;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.InvalidArgumentException;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.collect.Lists;
import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.BatchWriteRequest;
import com.google.firestore.v1.BatchWriteResponse;
import com.google.firestore.v1.BeginTransactionRequest;
import com.google.firestore.v1.BeginTransactionResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.CreateDocumentRequest;
import com.google.firestore.v1.Cursor;
import com.google.firestore.v1.DeleteDocumentRequest;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.GetDocumentRequest;
import com.google.firestore.v1.ListCollectionIdsRequest;
import com.google.firestore.v1.ListCollectionIdsResponse;
import com.google.firestore.v1.ListDocumentsRequest;
import com.google.firestore.v1.ListDocumentsResponse;
import com.google.firestore.v1.ListenRequest;
import com.google.firestore.v1.ListenResponse;
import com.google.firestore.v1.PartitionQueryRequest;
import com.google.firestore.v1.PartitionQueryResponse;
import com.google.firestore.v1.RollbackRequest;
import com.google.firestore.v1.RunQueryRequest;
import com.google.firestore.v1.RunQueryResponse;
import com.google.firestore.v1.UpdateDocumentRequest;
import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteRequest;
import com.google.firestore.v1.WriteResponse;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@javax.annotation.Generated("by GAPIC")
public class FirestoreClientTest {
  private static MockFirestore mockFirestore;
  private static MockServiceHelper serviceHelper;
  private FirestoreClient client;
  private LocalChannelProvider channelProvider;

  @BeforeClass
  public static void startStaticServer() {
    mockFirestore = new MockFirestore();
    serviceHelper =
        new MockServiceHelper(
            UUID.randomUUID().toString(), Arrays.<MockGrpcService>asList(mockFirestore));
    serviceHelper.start();
  }

  @AfterClass
  public static void stopServer() {
    serviceHelper.stop();
  }

  @Before
  public void setUp() throws IOException {
    serviceHelper.reset();
    channelProvider = serviceHelper.createChannelProvider();
    FirestoreSettings settings =
        FirestoreSettings.newBuilder()
            .setTransportChannelProvider(channelProvider)
            .setCredentialsProvider(NoCredentialsProvider.create())
            .build();
    client = FirestoreClient.create(settings);
  }

  @After
  public void tearDown() throws Exception {
    client.close();
  }

  @Test
  @SuppressWarnings("all")
  public void getDocumentTest() {
    String name2 = "name2-1052831874";
    Document expectedResponse = Document.newBuilder().setName(name2).build();
    mockFirestore.addResponse(expectedResponse);

    String name = "name3373707";
    GetDocumentRequest request = GetDocumentRequest.newBuilder().setName(name).build();

    Document actualResponse = client.getDocument(request);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    GetDocumentRequest actualRequest = (GetDocumentRequest) actualRequests.get(0);

    Assert.assertEquals(name, actualRequest.getName());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void getDocumentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String name = "name3373707";
      GetDocumentRequest request = GetDocumentRequest.newBuilder().setName(name).build();

      client.getDocument(request);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void listDocumentsTest() {
    String nextPageToken = "";
    Document documentsElement = Document.newBuilder().build();
    List<Document> documents = Arrays.asList(documentsElement);
    ListDocumentsResponse expectedResponse =
        ListDocumentsResponse.newBuilder()
            .setNextPageToken(nextPageToken)
            .addAllDocuments(documents)
            .build();
    mockFirestore.addResponse(expectedResponse);

    String parent = "parent-995424086";
    String collectionId = "collectionId-821242276";
    ListDocumentsRequest request =
        ListDocumentsRequest.newBuilder().setParent(parent).setCollectionId(collectionId).build();

    ListDocumentsPagedResponse pagedListResponse = client.listDocuments(request);

    List<Document> resources = Lists.newArrayList(pagedListResponse.iterateAll());
    Assert.assertEquals(1, resources.size());
    Assert.assertEquals(expectedResponse.getDocumentsList().get(0), resources.get(0));

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ListDocumentsRequest actualRequest = (ListDocumentsRequest) actualRequests.get(0);

    Assert.assertEquals(parent, actualRequest.getParent());
    Assert.assertEquals(collectionId, actualRequest.getCollectionId());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void listDocumentsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String parent = "parent-995424086";
      String collectionId = "collectionId-821242276";
      ListDocumentsRequest request =
          ListDocumentsRequest.newBuilder().setParent(parent).setCollectionId(collectionId).build();

      client.listDocuments(request);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void createDocumentTest() {
    String name = "name3373707";
    Document expectedResponse = Document.newBuilder().setName(name).build();
    mockFirestore.addResponse(expectedResponse);

    String parent = "parent-995424086";
    String collectionId = "collectionId-821242276";
    Document document = Document.newBuilder().build();
    CreateDocumentRequest request =
        CreateDocumentRequest.newBuilder()
            .setParent(parent)
            .setCollectionId(collectionId)
            .setDocument(document)
            .build();

    Document actualResponse = client.createDocument(request);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    CreateDocumentRequest actualRequest = (CreateDocumentRequest) actualRequests.get(0);

    Assert.assertEquals(parent, actualRequest.getParent());
    Assert.assertEquals(collectionId, actualRequest.getCollectionId());
    Assert.assertEquals(document, actualRequest.getDocument());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void createDocumentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String parent = "parent-995424086";
      String collectionId = "collectionId-821242276";
      Document document = Document.newBuilder().build();
      CreateDocumentRequest request =
          CreateDocumentRequest.newBuilder()
              .setParent(parent)
              .setCollectionId(collectionId)
              .setDocument(document)
              .build();

      client.createDocument(request);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void updateDocumentTest() {
    String name = "name3373707";
    Document expectedResponse = Document.newBuilder().setName(name).build();
    mockFirestore.addResponse(expectedResponse);

    Document document = Document.newBuilder().build();
    DocumentMask updateMask = DocumentMask.newBuilder().build();

    Document actualResponse = client.updateDocument(document, updateMask);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    UpdateDocumentRequest actualRequest = (UpdateDocumentRequest) actualRequests.get(0);

    Assert.assertEquals(document, actualRequest.getDocument());
    Assert.assertEquals(updateMask, actualRequest.getUpdateMask());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void updateDocumentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      Document document = Document.newBuilder().build();
      DocumentMask updateMask = DocumentMask.newBuilder().build();

      client.updateDocument(document, updateMask);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void deleteDocumentTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockFirestore.addResponse(expectedResponse);

    String name = "name3373707";

    client.deleteDocument(name);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    DeleteDocumentRequest actualRequest = (DeleteDocumentRequest) actualRequests.get(0);

    Assert.assertEquals(name, actualRequest.getName());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void deleteDocumentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String name = "name3373707";

      client.deleteDocument(name);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void batchGetDocumentsTest() throws Exception {
    String missing = "missing1069449574";
    ByteString transaction = ByteString.copyFromUtf8("-34");
    BatchGetDocumentsResponse expectedResponse =
        BatchGetDocumentsResponse.newBuilder()
            .setMissing(missing)
            .setTransaction(transaction)
            .build();
    mockFirestore.addResponse(expectedResponse);
    String database = "database1789464955";
    BatchGetDocumentsRequest request =
        BatchGetDocumentsRequest.newBuilder().setDatabase(database).build();

    MockStreamObserver<BatchGetDocumentsResponse> responseObserver = new MockStreamObserver<>();

    ServerStreamingCallable<BatchGetDocumentsRequest, BatchGetDocumentsResponse> callable =
        client.batchGetDocumentsCallable();
    callable.serverStreamingCall(request, responseObserver);

    List<BatchGetDocumentsResponse> actualResponses = responseObserver.future().get();
    Assert.assertEquals(1, actualResponses.size());
    Assert.assertEquals(expectedResponse, actualResponses.get(0));
  }

  @Test
  @SuppressWarnings("all")
  public void batchGetDocumentsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);
    String database = "database1789464955";
    BatchGetDocumentsRequest request =
        BatchGetDocumentsRequest.newBuilder().setDatabase(database).build();

    MockStreamObserver<BatchGetDocumentsResponse> responseObserver = new MockStreamObserver<>();

    ServerStreamingCallable<BatchGetDocumentsRequest, BatchGetDocumentsResponse> callable =
        client.batchGetDocumentsCallable();
    callable.serverStreamingCall(request, responseObserver);

    try {
      List<BatchGetDocumentsResponse> actualResponses = responseObserver.future().get();
      Assert.fail("No exception thrown");
    } catch (ExecutionException e) {
      Assert.assertTrue(e.getCause() instanceof InvalidArgumentException);
      InvalidArgumentException apiException = (InvalidArgumentException) e.getCause();
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void batchWriteTest() {
    BatchWriteResponse expectedResponse = BatchWriteResponse.newBuilder().build();
    mockFirestore.addResponse(expectedResponse);

    String database = "database1789464955";
    BatchWriteRequest request = BatchWriteRequest.newBuilder().setDatabase(database).build();

    BatchWriteResponse actualResponse = client.batchWrite(request);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    BatchWriteRequest actualRequest = (BatchWriteRequest) actualRequests.get(0);

    Assert.assertEquals(database, actualRequest.getDatabase());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void batchWriteExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String database = "database1789464955";
      BatchWriteRequest request = BatchWriteRequest.newBuilder().setDatabase(database).build();

      client.batchWrite(request);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void beginTransactionTest() {
    ByteString transaction = ByteString.copyFromUtf8("-34");
    BeginTransactionResponse expectedResponse =
        BeginTransactionResponse.newBuilder().setTransaction(transaction).build();
    mockFirestore.addResponse(expectedResponse);

    String database = "database1789464955";

    BeginTransactionResponse actualResponse = client.beginTransaction(database);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    BeginTransactionRequest actualRequest = (BeginTransactionRequest) actualRequests.get(0);

    Assert.assertEquals(database, actualRequest.getDatabase());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void beginTransactionExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String database = "database1789464955";

      client.beginTransaction(database);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void commitTest() {
    CommitResponse expectedResponse = CommitResponse.newBuilder().build();
    mockFirestore.addResponse(expectedResponse);

    String database = "database1789464955";
    List<Write> writes = new ArrayList<>();

    CommitResponse actualResponse = client.commit(database, writes);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    CommitRequest actualRequest = (CommitRequest) actualRequests.get(0);

    Assert.assertEquals(database, actualRequest.getDatabase());
    Assert.assertEquals(writes, actualRequest.getWritesList());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void commitExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String database = "database1789464955";
      List<Write> writes = new ArrayList<>();

      client.commit(database, writes);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void rollbackTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockFirestore.addResponse(expectedResponse);

    String database = "database1789464955";
    ByteString transaction = ByteString.copyFromUtf8("-34");

    client.rollback(database, transaction);

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    RollbackRequest actualRequest = (RollbackRequest) actualRequests.get(0);

    Assert.assertEquals(database, actualRequest.getDatabase());
    Assert.assertEquals(transaction, actualRequest.getTransaction());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void rollbackExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String database = "database1789464955";
      ByteString transaction = ByteString.copyFromUtf8("-34");

      client.rollback(database, transaction);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void runQueryTest() throws Exception {
    ByteString transaction = ByteString.copyFromUtf8("-34");
    int skippedResults = 880286183;
    RunQueryResponse expectedResponse =
        RunQueryResponse.newBuilder()
            .setTransaction(transaction)
            .setSkippedResults(skippedResults)
            .build();
    mockFirestore.addResponse(expectedResponse);
    String parent = "parent-995424086";
    RunQueryRequest request = RunQueryRequest.newBuilder().setParent(parent).build();

    MockStreamObserver<RunQueryResponse> responseObserver = new MockStreamObserver<>();

    ServerStreamingCallable<RunQueryRequest, RunQueryResponse> callable = client.runQueryCallable();
    callable.serverStreamingCall(request, responseObserver);

    List<RunQueryResponse> actualResponses = responseObserver.future().get();
    Assert.assertEquals(1, actualResponses.size());
    Assert.assertEquals(expectedResponse, actualResponses.get(0));
  }

  @Test
  @SuppressWarnings("all")
  public void runQueryExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);
    String parent = "parent-995424086";
    RunQueryRequest request = RunQueryRequest.newBuilder().setParent(parent).build();

    MockStreamObserver<RunQueryResponse> responseObserver = new MockStreamObserver<>();

    ServerStreamingCallable<RunQueryRequest, RunQueryResponse> callable = client.runQueryCallable();
    callable.serverStreamingCall(request, responseObserver);

    try {
      List<RunQueryResponse> actualResponses = responseObserver.future().get();
      Assert.fail("No exception thrown");
    } catch (ExecutionException e) {
      Assert.assertTrue(e.getCause() instanceof InvalidArgumentException);
      InvalidArgumentException apiException = (InvalidArgumentException) e.getCause();
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void writeTest() throws Exception {
    String streamId = "streamId-315624902";
    ByteString streamToken = ByteString.copyFromUtf8("122");
    WriteResponse expectedResponse =
        WriteResponse.newBuilder().setStreamId(streamId).setStreamToken(streamToken).build();
    mockFirestore.addResponse(expectedResponse);
    String database = "database1789464955";
    WriteRequest request = WriteRequest.newBuilder().setDatabase(database).build();

    MockStreamObserver<WriteResponse> responseObserver = new MockStreamObserver<>();

    BidiStreamingCallable<WriteRequest, WriteResponse> callable = client.writeCallable();
    ApiStreamObserver<WriteRequest> requestObserver = callable.bidiStreamingCall(responseObserver);

    requestObserver.onNext(request);
    requestObserver.onCompleted();

    List<WriteResponse> actualResponses = responseObserver.future().get();
    Assert.assertEquals(1, actualResponses.size());
    Assert.assertEquals(expectedResponse, actualResponses.get(0));
  }

  @Test
  @SuppressWarnings("all")
  public void writeExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);
    String database = "database1789464955";
    WriteRequest request = WriteRequest.newBuilder().setDatabase(database).build();

    MockStreamObserver<WriteResponse> responseObserver = new MockStreamObserver<>();

    BidiStreamingCallable<WriteRequest, WriteResponse> callable = client.writeCallable();
    ApiStreamObserver<WriteRequest> requestObserver = callable.bidiStreamingCall(responseObserver);

    requestObserver.onNext(request);

    try {
      List<WriteResponse> actualResponses = responseObserver.future().get();
      Assert.fail("No exception thrown");
    } catch (ExecutionException e) {
      Assert.assertTrue(e.getCause() instanceof InvalidArgumentException);
      InvalidArgumentException apiException = (InvalidArgumentException) e.getCause();
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void listenTest() throws Exception {
    ListenResponse expectedResponse = ListenResponse.newBuilder().build();
    mockFirestore.addResponse(expectedResponse);
    String database = "database1789464955";
    ListenRequest request = ListenRequest.newBuilder().setDatabase(database).build();

    MockStreamObserver<ListenResponse> responseObserver = new MockStreamObserver<>();

    BidiStreamingCallable<ListenRequest, ListenResponse> callable = client.listenCallable();
    ApiStreamObserver<ListenRequest> requestObserver = callable.bidiStreamingCall(responseObserver);

    requestObserver.onNext(request);
    requestObserver.onCompleted();

    List<ListenResponse> actualResponses = responseObserver.future().get();
    Assert.assertEquals(1, actualResponses.size());
    Assert.assertEquals(expectedResponse, actualResponses.get(0));
  }

  @Test
  @SuppressWarnings("all")
  public void listenExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);
    String database = "database1789464955";
    ListenRequest request = ListenRequest.newBuilder().setDatabase(database).build();

    MockStreamObserver<ListenResponse> responseObserver = new MockStreamObserver<>();

    BidiStreamingCallable<ListenRequest, ListenResponse> callable = client.listenCallable();
    ApiStreamObserver<ListenRequest> requestObserver = callable.bidiStreamingCall(responseObserver);

    requestObserver.onNext(request);

    try {
      List<ListenResponse> actualResponses = responseObserver.future().get();
      Assert.fail("No exception thrown");
    } catch (ExecutionException e) {
      Assert.assertTrue(e.getCause() instanceof InvalidArgumentException);
      InvalidArgumentException apiException = (InvalidArgumentException) e.getCause();
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void listCollectionIdsTest() {
    String nextPageToken = "";
    String collectionIdsElement = "collectionIdsElement1368994900";
    List<String> collectionIds = Arrays.asList(collectionIdsElement);
    ListCollectionIdsResponse expectedResponse =
        ListCollectionIdsResponse.newBuilder()
            .setNextPageToken(nextPageToken)
            .addAllCollectionIds(collectionIds)
            .build();
    mockFirestore.addResponse(expectedResponse);

    String parent = "parent-995424086";

    ListCollectionIdsPagedResponse pagedListResponse = client.listCollectionIds(parent);

    List<String> resources = Lists.newArrayList(pagedListResponse.iterateAll());
    Assert.assertEquals(1, resources.size());
    Assert.assertEquals(expectedResponse.getCollectionIdsList().get(0), resources.get(0));

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ListCollectionIdsRequest actualRequest = (ListCollectionIdsRequest) actualRequests.get(0);

    Assert.assertEquals(parent, actualRequest.getParent());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void listCollectionIdsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String parent = "parent-995424086";

      client.listCollectionIds(parent);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }

  @Test
  @SuppressWarnings("all")
  public void partitionQueryTest() {
    String nextPageToken = "";
    Cursor partitionsElement = Cursor.newBuilder().build();
    List<Cursor> partitions = Arrays.asList(partitionsElement);
    PartitionQueryResponse expectedResponse =
        PartitionQueryResponse.newBuilder()
            .setNextPageToken(nextPageToken)
            .addAllPartitions(partitions)
            .build();
    mockFirestore.addResponse(expectedResponse);

    String parent = "parent-995424086";
    PartitionQueryRequest request = PartitionQueryRequest.newBuilder().setParent(parent).build();

    PartitionQueryPagedResponse pagedListResponse = client.partitionQuery(request);

    List<Cursor> resources = Lists.newArrayList(pagedListResponse.iterateAll());
    Assert.assertEquals(1, resources.size());
    Assert.assertEquals(expectedResponse.getPartitionsList().get(0), resources.get(0));

    List<AbstractMessage> actualRequests = mockFirestore.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    PartitionQueryRequest actualRequest = (PartitionQueryRequest) actualRequests.get(0);

    Assert.assertEquals(parent, actualRequest.getParent());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  @SuppressWarnings("all")
  public void partitionQueryExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockFirestore.addException(exception);

    try {
      String parent = "parent-995424086";
      PartitionQueryRequest request = PartitionQueryRequest.newBuilder().setParent(parent).build();

      client.partitionQuery(request);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception
    }
  }
}
