/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.aliyun.dataworks.test;


import com.aliyun.dataworks.DemoStarter;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author guangzhen
 * @version $Id: DemoTester.java, v 0.1 2018年07月17日 16:57 guangzhen Exp $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoStarter.class)
public class DemoTester {}