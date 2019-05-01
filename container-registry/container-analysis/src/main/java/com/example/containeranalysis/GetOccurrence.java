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

// [START containeranalysis_get_occurrence]
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client;
import com.google.containeranalysis.v1beta1.OccurrenceName;
import io.grafeas.v1beta1.Occurrence;
import java.io.IOException;
import java.lang.InterruptedException;

public class GetOccurrence {
  /**
   * Retrieves and prints a specified Occurrence from the server
   * @param occurrenceId the identifier of the Occurrence to retrieve
   * @param projectId the GCP project the Occurrence belongs to
   * @return the requested Occurrence object
   * @throws IOException on errors creating the Grafeas client
   * @throws InterruptedException on errors shutting down the Grafeas client
   */
  public static Occurrence getOccurrence(String occurrenceId, String projectId) 
      throws IOException, InterruptedException {
    final OccurrenceName occurrenceName = OccurrenceName.of(projectId, occurrenceId);
    GrafeasV1Beta1Client client = GrafeasV1Beta1Client.create();
    Occurrence occ = client.getOccurrence(occurrenceName);
    System.out.println(occ);
    return occ;
  }
}
// [END containeranalysis_get_occurrence]
