package com.liwei.cloud.order.service;


import com.liwei.cloud.order.entity.Item;
import com.liwei.cloud.order.feign.ItemFeignClient;
import com.liwei.cloud.order.properties.OrderProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

    //spring框架对result方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ItemFeignClient itemFeignClient;

    @Autowired
    OrderProperties orderProperties;

    /**
     * 进行容错处理
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById3(Long id) {
        String itemUrl = "http://app-item/item/{id}";
        Item result = restTemplate.getForObject(itemUrl, Item.class, id);
        System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }


//    @Value("${myspcloud.item.url}")
//    private String itemUrl;
   // @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queyItemById(Long id){
        //该方法走eureka注册中心调用(去注册中心根据app-item查找服务,这种方式必须开启负载均衡@LoadBalance)
        //String itemUrl = "http://app-item/item/{id}";
        Item result = itemFeignClient.queryItemById(id);
        //Item result = restTemplate.getForObject(itemUrl, Item.class, id);
        //System.out.println("订单系统调用商品服务,result:" +result);
        System.out.println("=========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
        //return this.restTemplate.getForObject(orderProperties.getItem().getUrl() + id, Item.class);
    }
    public Item queryItemByIdFallbackMethod(Long id){
        return new Item(id,"查询商品出错！",null,null,null);
    }
//    public Item queryItemById(Long id) {
//        String serviceId = "app-item";
//        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
//        if(instances.isEmpty()){
//            return null;
//        }
//        // 为了演示，在这里只获取第一个实例
//        ServiceInstance serviceInstance = instances.get(0);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
//    }

}
