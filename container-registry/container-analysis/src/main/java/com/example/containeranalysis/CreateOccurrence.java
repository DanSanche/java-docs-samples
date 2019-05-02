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

// [START containeranalysis_create_occurrence]
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client;
import com.google.containeranalysis.v1beta1.NoteName;
import com.google.containeranalysis.v1beta1.ProjectName;
import io.grafeas.v1beta1.Occurrence;
import io.grafeas.v1beta1.Resource;
import io.grafeas.v1beta1.vulnerability.Details;
import java.io.IOException;
import java.lang.InterruptedException;

public class CreateOccurrence {
  /**
   * Creates and returns a new Occurrence associated with an existing Note
   * @param resourceUrl the Container Registry URL associated with the image
   *                 example: "https://gcr.io/project/image@sha256:foo"
   * @param noteId the identifier of the Note associated with this Occurrence
   * @param occProjectId the GCP project the Occurrence will be created under
   * @param noteProjectId the GCP project the associated Note belongs to
   * @return the newly created Occurrence object
   * @throws IOException on errors creating the Grafeas client
   * @throws InterruptedException on errors shutting down the Grafeas client
   */
  public static Occurrence createOccurrence(String resourceUrl, String noteId, 
      String occProjectId, String noteProjectId) throws IOException, InterruptedException {
    // String resourceUrl = "https://gcr.io/project/image@sha256:foo";
    // String noteId = "my-note";
    // String occProjectId = "my-project-id";
    // String noteProjectId = "my-project-id";
    final NoteName noteName = NoteName.of(noteProjectId, noteId);
    final String occProjectName = ProjectName.format(occProjectId);

    Occurrence.Builder occBuilder = Occurrence.newBuilder();
    occBuilder.setNoteName(noteName.toString());
    Details.Builder detailsBuilder = Details.newBuilder();
    // Details about the vulnerability instance can be added here
    occBuilder.setVulnerability(detailsBuilder);
    // Attach the occurrence to the associated image uri
    Resource.Builder resourceBuilder = Resource.newBuilder();
    resourceBuilder.setUri(resourceUrl);
    occBuilder.setResource(resourceBuilder);
    Occurrence newOcc = occBuilder.build();

    // Initialize client that will be used to send requests. After completing all of your requests, 
    // call the "close" method on the client to safely clean up any remaining background resources.
    GrafeasV1Beta1Client client = GrafeasV1Beta1Client.create();
    Occurrence result = client.createOccurrence(occProjectName, newOcc);
    return result;
  }
}
// [END containeranalysis_create_occurrence]
