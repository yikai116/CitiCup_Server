# CitiCup API文档

*系统报错返回示例：*

```json
{
    "status": {
        "code": 0,
        "msg": "系统错误"
    },
    "data": null
}
```



#### 1 登录注册模块

**1.1 注册**

API：

```
POST	http://localhost:8080/CitiCup/api/signUp
```

传入数据示例：

```json
{
    "phone":"15196673448",
    "psw":"123456",
    "verCode":"pdjq",
    "token":"359881030314356"
}
```

注：psw为加密之后的字符串，token是手机唯一标识码，verCode是验证码

返回数据示例：

```json
{
    "status": {
        "code": 1,
        "msg": "注册成功"
    },
    "data": null
}
```

code可能值：

```json
/**
 * 获取成功
 */
public static final int SUCCESS = 1;
/**
 * 验证码错误
 */
public static final int VERCODE_ERROR = -4;
/**
 * 用户存在
 */
public static final int USER_REGISTERED = -5;
```

**1.2 获取注册验证码**

API：

```
POST	http://localhost:8080/CitiCup/api/getSignUpVerCode
```

传入数据示例：

```json
{
    "phone":"15196673448"
}
```

注：psw为加密之后的字符串，token是手机唯一标识码

返回数据示例：

```json
{
    "status": {
        "code": 1,
        "msg": "获取成功"
    },
    "data": "57c4"
}
```

code可能值：

```json
/**
 * 获取成功
 */
public static final int SUCCESS = 1;
/**
 * 用户存在
 */
public static final int USER_REGISTERED = -5;
```

**1.3 登录**

API：

```
POST	http://localhost:8080/CitiCup/api/signIn
```

传入数据示例：

```json
{
    "phone":"15196673448",
    "psw":"123456",
    "token":"359881030314356"
}
```

注：psw为加密之后的字符串，token是手机唯一标识码

返回数据示例：

```json
{
    "status": {
        "code": 1,
        "msg": "登录成功"
    },
    "data": null
}
```

code可能值：

```json
/**
 * 获取成功
 */
public static final int SUCCESS = 1;
/**
 * 用户不存在
 */
public static final int NO_USER = -2;
/**
 * 密码错误
 */
public static final int PSW_ERROR = -3;
```

**1.4 找回密码**

API：

```
POST	http://localhost:8080/CitiCup/api/findPsw
```

传入数据示例：

```json
{
    "phone":"15196673448",
    "psw":"123456",
    "verCode":"pdjq"
}
```

注：psw为新密码加密之后的字符串，verCode是验证码

返回数据示例：

```json
{
    "status": {
        "code": 1,
        "msg": "修改成功"
    },
    "data": null
}
```

code可能值：

```json
/**
 * 获取成功
 */
public static final int SUCCESS = 1;
/**
 * 用户不存在
 */
public static final int NO_USER = -2;
/**
 * 验证码错误
 */
public static final int VERCODE_ERROR = -4;
```

**1.5 获取找回密码验证码**

API：

```
POST	http://localhost:8080/CitiCup/api/getFindPswVerCode
```

传入数据示例：

```json
{
    "phone":"15196673448"
}
```

注：psw为加密之后的字符串，token是手机唯一标识码

返回数据示例：

```json
{
    "status": {
        "code": 1,
        "msg": "获取成功"
    },
    "data": "57c4"
}
```

code可能值：

```json
/**
 * 获取成功
 */
public static final int SUCCESS = 1;
/**
 * 用户不存在
 */
public static final int NO_USER = -2;
```

**1.6 获取找回密码验证码**

API：

```
POST	http://localhost:8080/CitiCup/api/verToken
```

传入数据示例：

```json
{
    "token":"123"
}
```

注：psw为加密之后的字符串，token是手机唯一标识码

返回数据示例：

```json
{
    "status": {
        "code": 0,
        "msg": "验证码过期，请重新登录"
    },
    "data": null
}
```

code可能值：

```json
1:未过期		0:过期
```

