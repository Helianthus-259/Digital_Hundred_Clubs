package com.szbt.studentserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.szbt.studentserver.service.StudentService;
import com.szbt.studentserver.dao.mapper.StudentMapper;
//import com.szbt.studentserver.util.EmailUtil;
import org.example.dto.ActivityMemberDTO;
import org.example.dto.ClubDTO;
import org.example.enums.ResultCode;
import org.example.util.Result;
import org.example.enums.StatusCode;
import org.example.dto.UserInfoDTO;
import org.example.vo.DataVO;
import org.example.vo.SendMsg;
import org.example.vo.SingleCodeVO;
import org.example.vo.UploadSuccess;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.entity.Student;

import java.util.List;

/**
* @author 小壳儿
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-04-23 14:12:37
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Object savaAvatar(String relativePath, Integer studentId) {
        if (relativePath==null) return Result.send(StatusCode.UPLOAD_FILE_ERROR,new SendMsg("上传文件失败"));
        Student student = new Student();
        student.setStudentId(studentId);
        student.setImageUrl(relativePath);
        int ok = studentMapper.updateById(student);
        System.out.println(ok);
        if (ok<=0) return Result.send(StatusCode.UPLOAD_FILE_ERROR,new SendMsg("上传文件失败"));
        return Result.success(new UploadSuccess(relativePath));
    }

    @Override
    public Object getStudentInfoById(Integer id, List<ClubDTO> clubDTOS, List<ActivityMemberDTO> activityMemberDTOS) {

        MPJLambdaWrapper<Student> wrapper = new MPJLambdaWrapper<Student>()
                .selectAll(Student.class)
                .eq(Student::getStudentId,id);
        try{
            UserInfoDTO userInfoDTO = studentMapper.selectJoinOne(UserInfoDTO.class, wrapper);
            userInfoDTO.setAchievements(activityMemberDTOS);
            userInfoDTO.setClubs(clubDTOS);
            System.out.println(userInfoDTO);
            return Result.success(new DataVO(ResultCode.GET_USER_INFO,userInfoDTO));
        }catch (Exception e)
        {
            return Result.send(StatusCode.GET_STUDENT_INFO_ERROR,new SendMsg("获取学生信息失败"));
        }
    }

    @Override
    public Object studentInfoUpdate(Student student) {
        int updateById = studentMapper.updateById(student);
        if (updateById<=0) return Result.send(StatusCode.UPDATE_STUDENT_INFO_ERROR,new SendMsg("更新学生信息失败"));
        return Result.success(new SingleCodeVO(ResultCode.UPDATE_USER_INFO));
    }

    @Override
    public List<Student> getStudentByNumber(List<String> number) {
        MPJLambdaWrapper<Student> wrapper = new MPJLambdaWrapper<Student>()
                .selectAll(Student.class)
                .in(Student::getStudentNumber, number);
        List<Student> studentList = studentMapper.selectJoinList(Student.class, wrapper);
        System.out.println(studentList);
        return studentList;
    }

//    @Autowired
//    private EmailUtil emailUtil;
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    // 先用map存储已发送的验证码，后面可以改用redis
//    private final Map<String, String> emailCodeMap = new ConcurrentHashMap<>(16);
//
//    @Override
//    public Object login(String email, String password) {
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("email", email);
//        Student student = studentMapper.selectOne(queryWrapper,true);
//        if(SecurityUtil.decrypt(password,student.getPwd()))
//            return Result.success(new LRSuccess(ResultCode.REGISTER_LOGIN,"admin",student.getStudentId()));
//        return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("登录失败"));
//    }

//    @Override
//    public Object register(String email, String verifyCode, String password) {
//        if(!verifyCode.equals(emailCodeMap.get(email))) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("email", email);
//        if(studentMapper.exists(queryWrapper)) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
//        val student = new Student();
//        student.setEmail(email);
//        student.setPwd(SecurityUtil.encrypt(password));
//        if(studentMapper.insert(student)==0) return Result.send(StatusCode.REGISTER_LOGIN_ERROR,new SendMsg("注册失败"));
//        return Result.success(new LRSuccess(ResultCode.REGISTER_LOGIN,"admin",student.getStudentId()));
//    }

//    @Override
//    public Object sendVerifyCode(String email) {
//        String verifyCode = emailUtil.generateCode (6);
//        if (emailUtil.sendMail(email,"数字百团验证服务","验证码:"+verifyCode))
//        {
//            // 保存验证码
//            emailCodeMap.put(email, verifyCode);
//            System.out.println(emailCodeMap.get(email));
//            return Result.success(new SendMsg("发送成功"));
//        }
//        return Result.send(StatusCode.SEND_VERIFY_CODE_ERROR,new SendMsg("发送失败"));
//    }

//    @Override
//    public Object checkImageVerifyCode(boolean ok)
//    {
//        if(ok) return Result.success(ok);
//        else return Result.send(StatusCode.VERIFY_IMAGE_CODE_ERROR,"图形验证码错误！");
//    }
}




