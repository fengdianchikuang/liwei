package com.liwei.cloud.order.fallback;

import com.liwei.cloud.order.entity.Item;
import com.liwei.cloud.order.feign.ItemFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
@Component
public class ItemServiceFallback implements ItemFeignClient {
    /**
     * 服务降级的方法要和原方法一致（名称，参数列表）
     * @param id
     * @return
     */
    @Override
    public Item queryItemById(@PathVariable("id") Long id) {
        return new Item(null,"服务降级方法queryItemById",null,"服务降级的方法queryItemByID",null);

    }
}
