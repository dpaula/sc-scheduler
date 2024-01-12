package com.dpaula.scscheduler.api.listener;

import com.dpaula.scscheduler.api.model.AppModel;
import com.dpaula.scscheduler.domain.entity.App;
import com.dpaula.scscheduler.domain.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author Fernando de Lima on 12/01/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppCreatedEventListener implements Consumer<AppModel> {

    private final AppRepository apps;

    @Override
    public void accept(AppModel appModel) {
        log.info("App criado " + appModel.getId());

        final var app = App.builder()
                .id(appModel.getId())
                .name(appModel.getName())
                .address(appModel.getAddress())
                .build();

        apps.saveAndFlush(app);
    }
}
