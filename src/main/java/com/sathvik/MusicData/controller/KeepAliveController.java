package com.sathvik.MusicData.controller;

import org.springframework.web.bind.annotation.*;

import static com.sathvik.MusicData.constants.ResponseMessages.KEEP_ALIVE;

@RestController
@RequestMapping(value = "/keep-alive")
@ResponseBody
@CrossOrigin
public class KeepAliveController {
    @GetMapping()
    public String keepAlive() {
        return KEEP_ALIVE;
    }
}
