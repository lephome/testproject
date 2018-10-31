package org.leo.test.redistest.web;

import org.leo.test.redistest.cmd.RedisKeysCmdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cmd")
public class RedisCmdController {
	@Autowired
    private RedisKeysCmdService redisCmdService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

   @RequestMapping(value="/get/{key}",method = RequestMethod.GET)
   public String get(@PathVariable(value = "key") String key) {
	   String value = redisCmdService.get(key);
	   log.info("RedisCmdController get, key:" + key + " value:" + value);
       return value;
   }
    
//    @RequestMapping("")
//    public String learn(Model model){
//        model.addAttribute("ctx", getContextPath()+"/");
//        return "learn-resource";
//    }
//
//    /**
//     * 查询教程列表
//     * @param page
//     * @return
//     */
//    @RequestMapping(value = "/queryLeanList",method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxObject queryLearnList(Page<LeanQueryLeanListReq> page){
//        List<LearnResource> learnList=learnService.queryLearnResouceList(page);
//        PageInfo<LearnResource> pageInfo =new PageInfo<LearnResource>(learnList);
//        return AjaxObject.ok().put("page", pageInfo);
//    }
//
//    /**
//     * 新添教程
//     * @param learn
//     */
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxObject addLearn(@RequestBody LearnResource learn){
//        learnService.save(learn);
//        return AjaxObject.ok();
//    }
//
//    /**
//     * 修改教程
//     * @param learn
//     */
//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxObject updateLearn(@RequestBody LearnResource learn){
//        learnService.updateNotNull(learn);
//        return AjaxObject.ok();
//    }
//
//    /**
//     * 删除教程
//     * @param ids
//     */
//    @RequestMapping(value="/delete",method = RequestMethod.POST)
//    @ResponseBody
//    public AjaxObject deleteLearn(@RequestBody Long[] ids){
//        learnService.deleteBatch(ids);
//        return AjaxObject.ok();
//    }
//
//    /**
//     * 获取教程
//     * @param id
//     */
//    @RequestMapping(value="/resource/{id}",method = RequestMethod.GET)
//    @ResponseBody
//    public LearnResource qryLearn(@PathVariable(value = "id") Long id){
//       LearnResource lean= learnService.selectByKey(id);
//        return lean;
//    }
}
