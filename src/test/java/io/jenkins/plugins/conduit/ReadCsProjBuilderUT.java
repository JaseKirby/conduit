package io.jenkins.plugins.conduit;

import hudson.FilePath;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class ReadCsProjBuilderUT {
    @Rule public JenkinsRule j = new JenkinsRule();

    @Test
    public void readCsProjFileTest() throws Exception {
        String csProjFileName = "csharpProject.csproj";
        WorkflowJob project = j.createProject(WorkflowJob.class);
        FilePath wk = j.jenkins.getWorkspaceFor(project);
        FilePath csProjInWk= wk.child(csProjFileName);
        csProjInWk.copyFrom(getClass().getResourceAsStream(csProjFileName));
        System.out.println(csProjInWk.toURI());

        String cps= String.format("node{conduitReadCsProj '%s'}", csProjFileName);
        project.setDefinition(new CpsFlowDefinition(cps, true));
        WorkflowRun run = j.buildAndAssertSuccess(project);
        j.assertLogContains("file found:\t" + csProjFileName, run);

    }
}
