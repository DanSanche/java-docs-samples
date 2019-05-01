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

// [START containeranalysis_delete_occurrence]
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client;
import com.google.containeranalysis.v1beta1.OccurrenceName;
import java.io.IOException;
import java.lang.InterruptedException;

public class DeleteOccurrence {
  /**
   * Deletes an existing Occurrence from the server
   * @param occurrenceId the identifier of the Occurrence to delete
   * @param projectId the GCP project the Occurrence belongs to
   * @throws IOException on errors creating the Grafeas client
   * @throws InterruptedException on errors shutting down the Grafeas client
   */
  public static void deleteOccurrence(String occurrenceId, String projectId) 
      throws IOException, InterruptedException {
    final OccurrenceName occurrenceName = OccurrenceName.of(projectId, occurrenceId);

    GrafeasV1Beta1Client client = GrafeasV1Beta1Client.create();
    client.deleteOccurrence(occurrenceName);
  }
}
// [END containeranalysis_delete_occurrence]
