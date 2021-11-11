/*
* Copyright (c) 2021 Qi Keira Zhang. All rights reserved.
*/

import jenkins.util.VirtualFile

/**
 * Retrieve job build artifact file's content
 * @param build
 * @param filename_regex
 * @return
 */
def call(def build, def filename_regex){
    def artifacts = build.getArtifacts()
    println "DEBUG: build artifacts: $artifacts"

    println "DEBUG: Look for artifacts with pattern: $filename_regex"
    def artifact_file = artifacts.find { it.name ==~ /$filename_regex/ }
    println "DEBUG: Found artifacts: $artifact_file"

    String artifact_name = artifact_file.relativePath
    println "DEBUG: Artifact relative path: $artifact_name"

    VirtualFile artifact_virtual_file = build.getArtifactManager().root().child(artifact_name)
    println "DEBUG: Artifact virutal file: $artifact_virtual_file"

    String artifact_content = getVirtualFileContent(artifact_virtual_file)
    return artifact_content
}

def getVirtualFileContent(VirtualFile artifact_virtual_file){
    InputStream is = null
    try {
        is = artifact_virtual_file.open()
        return is.text
    } finally {
        is?.close()
    }
}
