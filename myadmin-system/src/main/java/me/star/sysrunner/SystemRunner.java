package me.star.sysrunner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Zheng Jie
 * @description 程序启动后处理数据
 * @date 2025-01-13
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SystemRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
    }
}
