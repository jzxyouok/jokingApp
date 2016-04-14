package com.example.jokingApp.injector.component;

import android.app.Service;

import com.example.jokingApp.injector.PerService;
import com.example.jokingApp.injector.moduel.ServiceModule;
import com.example.jokingApp.service.MessageService;

import dagger.Component;

/**
 * Created by idea-pc on 2016/4/13.
 */
@PerService
@Component( modules = {ServiceModule.class},dependencies = ApplicationComponent.class)
public interface ServiceComponent {

    void inject(MessageService messageService);

}
