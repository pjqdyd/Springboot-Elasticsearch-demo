package com.pjqdyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**   
 * @Description:  [启动类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@SpringBootApplication
public class EsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsDemoApplication.class, args);
        System.out.println("------------应用已启动----------");
    }

}
