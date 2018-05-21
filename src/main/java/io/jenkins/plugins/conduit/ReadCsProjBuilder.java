package io.jenkins.plugins.conduit;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

import jenkins.tasks.SimpleBuildStep;
import org.apache.commons.io.IOUtils;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;


public class ReadCsProjBuilder extends Builder implements SimpleBuildStep {
    private final String file;

    @DataBoundConstructor
    public ReadCsProjBuilder(String file) {
        this.file = file;
    }

    public String getFile() { return this.file; }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
            throws InterruptedException, IOException {
        FilePath f = workspace.child(this.file);
        InputStream is = f.read();
        String contents = IOUtils.toString(is);
        listener.getLogger().println(contents);

//        try {
//            slurpCsProjXml(is);
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
    }

//    private GPathResult slurpCsProjXml(InputStream is)
//            throws ParserConfigurationException, SAXException, IOException {
//        return new XmlSlurper().parse(is);
//    }

    @Symbol("conduitReadCsProj")
    @Extension
    public static final class ReadCsProjBuilderDescriptor extends BuildStepDescriptor<Builder> {
        @Override
        public String getDisplayName() {
            return "read csproj file";
        }
        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return false;
        }
    }
}
