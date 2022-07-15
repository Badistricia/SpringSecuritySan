package com.badistricia.user.mapper;

import com.badistricia.user.model.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author badistricia
 * @since 2022-06-15
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long userid);
}
