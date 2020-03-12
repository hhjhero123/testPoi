package cn.web.enumTest;

public class JudgeRoleController {

    public String judge(String roleName ) {
    // 一行代码搞定！之前的if/else没了！
        return RoleEnum.valueOf(roleName).op();
    }


    public static void main(String[] args) {
        System.out.println(RoleEnum.valueOf("ROLE_ORDER_ADMIN").op());
    }

}
