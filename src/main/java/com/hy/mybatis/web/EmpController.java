package com.hy.mybatis.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.mybatis.pojo.Dept;
import com.hy.mybatis.pojo.Employee;
import com.hy.mybatis.service.DeptService;
import com.hy.mybatis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("mybatis")
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private DeptService deptService;

    /**
     * 根据条件查询所有
     * @return
     */
    @GetMapping(value = "/empAll")
    public ModelAndView empServlet(Employee employee,@RequestParam(defaultValue ="1") Integer page) throws IOException {
        System.out.println("empAll的employee======" + employee);
        ModelAndView modelAndView=new ModelAndView("empList");

        //page是页数，pageSize是一页行数，true true表示需要统计总数
        PageHelper.startPage(page, 5,true);

        List<Employee> employees = empService.queryAll(employee,page);

        PageInfo<Employee> info=new PageInfo<>(employees);
        //总页数
        int totalPage = info.getPages();
        //总数据条数
        long total = info.getTotal();

        modelAndView.addObject("list", employees);
        modelAndView.addObject("page",page);
        modelAndView.addObject("totalPage",totalPage);
        System.out.println("-----跳转至emplist.jsp------");
        return modelAndView;
    }

    @RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView=new ModelAndView("empList");
        return modelAndView;
    }
    /**
     * 查询所有部门
     * @return
     */
    @PostMapping(value = "/deptList")
    @ResponseBody
    public List<Dept> deptList(){
        List<Dept> list=deptService.queryDept();
        return list;
    }

    /**
     * 添加员工
     * @param employee
     * @throws IOException
     */
    @PostMapping(value = "/login/addEmp")
    public String addEmp(Employee employee,@RequestParam("file") MultipartFile image,HttpServletRequest request) throws  IOException {
        System.out.println("addEmp的employee============"+employee);
        String originalFilename = image.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuidname= UUID.randomUUID().toString();
        image.transferTo(new File(request.getServletContext().getRealPath("/")+"down/"+uuidname+suffix));
        employee.setImage(uuidname+suffix);
        System.out.println("image======"+uuidname + suffix);
        employee.setGender(employee.getGender()=="女"?"F":"M");
        int save = empService.insert(employee);
        System.out.println("save"+save);
        employee=new Employee();
        System.out.println("null employee" + employee);
        if(save!=0){
            //添加成功
            return "redirect:/mybatis/empAll";
        }else{
            //添加失败
            return "addEmp";
        }
    }

    /**
     * 删除员工
     * @return
     */
    @RequestMapping("/login/delEmp")
    public String delEmp(String id) throws IOException {
        Employee employee=new Employee();
        employee.setId(id);
        System.out.println("employee id===="+id);
        System.out.println("employee<delete>====" + employee);
        int delete = empService.delete(employee);
        System.out.println("delete==" + delete);
        //删除成功跳转至empAll
        return "redirect:/mybatis/empAll";
    }

    /**
     * 根据id查询emp
     * @param id
     * @return
     */
    @RequestMapping("/login/empInfo")
    public ModelAndView empInfo(String id) throws IOException {
        ModelAndView modelAndView=new ModelAndView("updEmp");
        Employee employee = empService.queryById(id);
        System.out.println("empInfo的employee=====" + employee);
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }

    /**
     * 修改员工信息
     * @param employee
     * @throws IOException
     */
    @RequestMapping("/login/updEmp")
    public String updEmp(Employee employee,@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request) throws IOException {
        System.out.println("update before===="+employee);
        if(!multipartFile.isEmpty()){
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuidname= UUID.randomUUID().toString();
            multipartFile.transferTo(new File(request.getServletContext().getRealPath("/")+"down/"+uuidname+suffix));
            employee.setImage(uuidname+suffix);
        }
        System.out.println("update after===="+employee);
        int update = empService.update(employee);
        System.out.println("update===" + update);
        if(update!=0){
            //修改成功
            return "redirect:/mybatis/empAll";
        }else{
            //修改失败
            return "/mybatis/login/empInfo";
        }
    }

    /**
     * 批量删除
     * @param check_del
     * @return
     */
    @RequestMapping("/login/delBatch")
    public String delBatch(String[] check_del) throws IOException {
        empService.deleteBatch(check_del);
        return "redirect:/mybatis/empAll";
    }

    /**
     * 登录
     * @param request
     * @param username
     * @return
     */
    @PostMapping("/toLoad")
    public String toLoad(HttpServletRequest request,String username){
        System.out.println("toload的username=====" + username);
        if(empService.queryName(username)){
            request.getSession().setAttribute("username",username);

            System.out.println("跳转至empall");
            //此员工存在
            return "redirect:/mybatis/empAll";
        }
        System.out.println("继续登录");
        //此员工不存在
        return "load";
    }

    /**
     * 下载
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileDownLoad")
    public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request,String filename) throws Exception{
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/"+filename);//得到文件所在位置
        InputStream in=new FileInputStream(new File(realPath));//将该文件加入到输入流之中
        byte[] body=null;
        body=new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        in.read(body);//读入到输入流里面

        filename=new String(filename.getBytes("gbk"),"iso8859-1");//防止中文乱码
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+filename);
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;

        //public ResponseEntity（T  body，
        //                       MultiValueMap < String，String > headers，
        //                       HttpStatus  statusCode）
        //HttpEntity使用给定的正文，标题和状态代码创建一个新的。
        //参数：
        //body - 实体机构
        //headers - 实体头
        //statusCode - 状态码
    }

}
