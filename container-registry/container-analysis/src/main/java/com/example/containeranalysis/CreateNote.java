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

// [START containeranalysis_create_note]
import com.google.cloud.devtools.containeranalysis.v1beta1.GrafeasV1Beta1Client;
import com.google.containeranalysis.v1beta1.ProjectName;
import io.grafeas.v1beta1.Note;
import io.grafeas.v1beta1.vulnerability.Vulnerability;
import java.io.IOException;
import java.lang.InterruptedException;


public class CreateNote {

  /**
   * Creates and returns a new Note
   * @param noteId A user-specified identifier for the Note.
   * @param projectId the GCP project the Note will be created under
   * @return the newly created Note object
   * @throws IOException on errors creating the Grafeas client
   * @throws InterruptedException on errors shutting down the Grafeas client
   */
  public static Note createNote(String noteId, String projectId) 
      throws IOException, InterruptedException {
    final String projectName = ProjectName.format(projectId);

    Note.Builder noteBuilder = Note.newBuilder();
    Vulnerability.Builder vulBuilder = Vulnerability.newBuilder();
    // Details about the your vulnerability can be added here
    // Example: vulBuilder.setSeverity(Severity.CRITICAL);
    noteBuilder.setVulnerability(vulBuilder);
    Note newNote = noteBuilder.build();

    GrafeasV1Beta1Client client = GrafeasV1Beta1Client.create();
    Note result = client.createNote(projectName, noteId, newNote);
    return result;
  }
}
// [END containeranalysis_create_note]
