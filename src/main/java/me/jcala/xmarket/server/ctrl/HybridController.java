package me.jcala.xmarket.server.ctrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.jcala.xmarket.server.conf.ApiConf;
import me.jcala.xmarket.server.entity.document.Team;
import me.jcala.xmarket.server.entity.dto.Result;
import me.jcala.xmarket.server.service.inter.HybridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 与学校相关的控制器
 */
@Api("跟学校有关的api")
@RestController
public class HybridController {

    private HybridService hybridService;

    @Autowired
    public HybridController(HybridService hybridService) {
        this.hybridService = hybridService;
    }

    @ApiOperation(value = "创建一个新的志愿队",response = Result.class,produces = "application/json;charset=UTF-8")
    @PostMapping(value = ApiConf.create_team,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createTeam(Team team){
        // TODO: 16-12-10  没有设置userId
        return hybridService.createTeam(team);
    }

    @ApiOperation(value = "获取本校所有志愿队列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = ApiConf.get_school_teams,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> gainTeamList(@PathVariable("schoolName") String schoolName){

        return hybridService.getTeamListBySchoolName(schoolName);
    }


    @ApiOperation(value = "获取学校名称列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = ApiConf.get_school_list,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> gainSchoolList(){
        return hybridService.getSchoolList();
    }

    @ApiOperation(value = "获取图片资源",response = byte[].class,produces = "application/json;charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code=404,message="没有找到该图片")
    })
    @GetMapping(value = ApiConf.get_img,produces = "image/jpeg;image/png;image/gif")
    public ResponseEntity<byte[]> gainImg(@PathVariable("dir")String dir, @PathVariable("picName") String picName)
            throws RuntimeException {
        return hybridService.gainPic(dir,picName);
    }

    @ApiOperation(value = "获取所有的商品分类",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "tags/get",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> gainTradeTagList(int kind){

        if (kind==1){
            return hybridService.getTradeTagList();//kind为1返回TradeTag列表
        }else{
            return hybridService.getTradeTagNameList();//kind为2返回String列表
        }

    }

}