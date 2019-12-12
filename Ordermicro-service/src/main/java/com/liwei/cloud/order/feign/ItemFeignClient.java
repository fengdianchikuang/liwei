package com.liwei.cloud.order.feign;

import com.liwei.cloud.order.entity.Item;
import com.liwei.cloud.order.fallback.ItemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 声明这是一个feigen客户端，并指名服务id
 */
@FeignClient(value = "app-item",fallback = ItemServiceFallback.class)
public interface ItemFeignClient {
    /**
     * 这里定义了类似SpringMvc的用法,就可以进行Restful方式进行调用
     * @param id
     * @return
     */
    @RequestMapping(value = "/item/{id}",method = RequestMethod.GET)
    Item queryItemById(@PathVariable("id") Long id);

}
