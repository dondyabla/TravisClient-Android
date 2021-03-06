package com.khmelenko.lab.travisclient.dagger;

import com.khmelenko.lab.travisclient.network.retrofit.github.GitHubRestClient;
import com.khmelenko.lab.travisclient.network.retrofit.raw.RawClient;
import com.khmelenko.lab.travisclient.network.retrofit.travis.TravisRestClient;
import com.khmelenko.lab.travisclient.task.TaskHelper;
import com.khmelenko.lab.travisclient.task.TaskManager;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

/**
 * TaskModule for testing
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
@Module
public class TestTaskModule {

    @Singleton
    @Provides
    public TaskHelper provideTaskHelper(TravisRestClient travisRestClient,
                                        GitHubRestClient gitHubRestClient,
                                        RawClient rawClient, EventBus eventBus) {
        return new TaskHelper(travisRestClient, gitHubRestClient, rawClient, eventBus);
    }

    @Singleton
    @Provides
    public TaskManager provideTaskManager(TaskHelper taskHelper) {
        return Mockito.spy(new TaskManager(taskHelper));
    }

}
