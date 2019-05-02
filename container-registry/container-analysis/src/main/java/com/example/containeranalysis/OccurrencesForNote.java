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

// [START containeranalysis_occurrences_for_note]
import static java.lang.Thread.sleep;

import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client;
import com.google.containeranalysis.v1beta1.NoteName;
import io.grafeas.v1beta1.ListNoteOccurrencesRequest;
import io.grafeas.v1beta1.Occurrence;
import java.io.IOException;
import java.lang.InterruptedException;

public class OccurrencesForNote {  
  /**
   * Retrieves all the Occurrences associated with a specified Note
   * Here, all Occurrences are printed and counted
   * @param noteId the Note's unique identifier
   * @param projectId the GCP project the Note belongs to
   * @return number of Occurrences found
   * @throws IOException on errors creating the Grafeas client
   * @throws InterruptedException on errors shutting down the Grafeas client
   */
  public static int getOccurrencesForNote(String noteId, String projectId) 
      throws IOException, InterruptedException {
    // String noteId = "my-note";
    // String projectId = "my-project-id";
    final NoteName noteName = NoteName.of(projectId, noteId);

    ListNoteOccurrencesRequest request = ListNoteOccurrencesRequest.newBuilder()
                                                                   .setName(noteName.toString())
                                                                   .build();

    // Initialize client that will be used to send requests. After completing all of your requests, 
    // call the "close" method on the client to safely clean up any remaining background resources.
    GrafeasV1Beta1Client client = GrafeasV1Beta1Client.create();
    int i = 0;
    for (Occurrence o : client.listNoteOccurrences(request).iterateAll()) {
      // Write custom code to process each Occurrence here
      System.out.println(o.getName());
      i = i + 1;
    }
    return i;
  }
}
// [END containeranalysis_occurrences_for_note]
