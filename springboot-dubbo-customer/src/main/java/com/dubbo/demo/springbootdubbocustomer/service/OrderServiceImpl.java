package com.dubbo.demo.springbootdubbocustomer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubbocommon.service.OrderService;
import com.dubbo.demo.springbootdubbocommon.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;



@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);
    /**
     * loadbalance
     * 负载均衡策略
     * Random LoadBalance
     * 随机，按权重设置随机概率。
     * 在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。
     * RoundRobin LoadBalance
     * 轮询，按公约后的权重设置轮询比率。
     * 存在慢的提供者累积请求的问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。
     * LeastActive LoadBalance
     * 最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
     * 使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
     * ConsistentHash LoadBalance
     * 一致性 Hash，相同参数的请求总是发到同一提供者。
     * 当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。
     * 算法参见：http://en.wikipedia.org/wiki/Consistent_hashing
     * 缺省只对第一个参数 Hash，如果要修改，请配置 <dubbo:parameter key="hash.arguments" value="0,1" />
     * 缺省用 160 份虚拟节点，如果要修改，请配置 <dubbo:parameter key="hash.nodes" value="320" />
     *
     * cache:用于加速热门数据的访问速度，Dubbo 提供声明式缓存，以减少用户加缓存的工作量
     * lru 基于最近最少使用原则删除多余缓存，保持最热的数据被缓存。
     * threadlocal 当前线程缓存，比如一个页面渲染，用到很多 portal，每个 portal 都要去查用户信息，通过线程缓存，可以减少这种多余访问。
     * jcache 与 JSR107 集成，可以桥接各种缓存实现。
     */
	@Reference(loadbalance="random",timeout=1000,version = "1.0.0",retries = 2,url = "dubbo://localhost:20880",validation = "true",cache="lru") //dubbo直连
	UserService userService;

	@HystrixCommand(fallbackMethod="hello")
	@Override
	public List<User> initOrder(String userId) {
		//int i=1/0;
		//1、查询用户的收货地址
		List<User> addressList = userService.findListUser();
		return addressList;
	}

    /**
     * 容错
     * @param userId
     * @return
     */
   public List<User> hello(String userId) {
       logger.info("服务容错");
       User user = new User(1,"2","3",1,0);
       User user1 = new User(1,"2","3",1,0);
       User user2 = new User(1,"2","3",1,0);
       return Arrays.asList(user,user1,user2);
   }
}
