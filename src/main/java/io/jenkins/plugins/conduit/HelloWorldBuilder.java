package io.jenkins.plugins.conduit;

import java.io.IOException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.YesNoMaybe;
import jenkins.tasks.SimpleBuildStep;

public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

  private final String name;

  @DataBoundConstructor
  public HelloWorldBuilder(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
			throws InterruptedException, IOException {
    listener.getLogger().println("What up " + this.name + "!");
  }
  
  @Symbol("conduitHello")
  @Extension(dynamicLoadable = YesNoMaybe.NO)
  public static final class HelloWorldBuilderDescriptor extends BuildStepDescriptor<Builder> {
    @Override
    public boolean isApplicable(Class<? extends AbstractProject> jobType) {
      return false;
    }
  }

}