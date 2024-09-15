package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("添加菜品")
    public Result<String> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> page(DishPageQueryDTO queryDTO){
        log.info("菜品分页查询：{}", queryDTO);
        PageResult pageResult = dishService.pageQuery(queryDTO);
        return  Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("删除菜品接口")
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品：{}",ids);
        dishService.deleteBatch(ids);
        return  Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("获取菜品详情")
    public  Result<DishVO> findById(@PathVariable Long id){
        log.info("查询菜品详情：{}",id);
        DishVO dishVO  = dishService.getDishById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return  Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类获取菜品")
    public  Result<List<Dish>> listByCategoryId(Long categoryId){
        log.info("根据ID获取菜品{}", categoryId);
        List<Dish> dishVOS = dishService.list(categoryId);
        return Result.success(dishVOS);
    }
}
