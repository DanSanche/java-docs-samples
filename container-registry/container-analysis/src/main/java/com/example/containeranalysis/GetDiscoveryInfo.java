/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.containeranalysis;

// [START containeranalysis_discovery_info]
import static java.lang.Thread.sleep;

import com.google.cloud.devtools.containeranalysis.v1beta1.ContainerAnalysisV1Beta1Client;
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client;
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client.ListNoteOccurrencesPagedResponse;
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client.ListOccurrencesPagedResponse;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.containeranalysis.v1beta1.NoteName;
import com.google.containeranalysis.v1beta1.OccurrenceName;
import com.google.containeranalysis.v1beta1.ProjectName;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import io.grafeas.v1beta1.ListNoteOccurrencesRequest;
import io.grafeas.v1beta1.ListOccurrencesRequest;
import io.grafeas.v1beta1.Note;
import io.grafeas.v1beta1.Occurrence;
import io.grafeas.v1beta1.Resource;
import io.grafeas.v1beta1.UpdateNoteRequest;
import io.grafeas.v1beta1.UpdateOccurrenceRequest;
import io.grafeas.v1beta1.vulnerability.Details;
import io.grafeas.v1beta1.vulnerability.Severity;
import io.grafeas.v1beta1.vulnerability.Vulnerability;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.concurrent.TimeUnit;

public class GetDiscoveryInfo {
  /**
   * Retrieves and prints the Discovery Occurrence created for a specified image
   * The Discovery Occurrence contains information about the initial scan on the image
   * @param resourceUrl the Container Registry URL associated with the image
   *                 example: "https://gcr.io/project/image@sha256:foo"
   * @param projectId the GCP project the image belongs to
   * @throws IOException on errors creating the Grafeas client
   * @throws InterruptedException on errors shutting down the Grafeas client
   */
  public static void getDiscoveryInfo(String resourceUrl,String projectId) 
      throws IOException, InterruptedException {
    String filterStr = "kind=\"DISCOVERY\" AND resourceUrl=\"" + resourceUrl + "\"";
    final String projectName = ProjectName.format(projectId);

    GrafeasV1Beta1Client client = GrafeasV1Beta1Client.create();
    for (Occurrence o : client.listOccurrences(projectName, filterStr).iterateAll()) {
      System.out.println(o);
    }
  }
}
// [END containeranalysis_discovery_info]
