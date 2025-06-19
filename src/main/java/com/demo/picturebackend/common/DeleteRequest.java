package com.demo.picturebackend.common;

import java.io.Serializable;

/**
 * ClassName:DeleteRequest
 * Package:com.demo.picturebackend.common
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/17 - 19:48
 * @Version: v1.0
 */
public class DeleteRequest implements Serializable {

    /**
     * 请求id
     */
    private Long id;

    private static final long serialVersionUID = 9084479869674883386L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
