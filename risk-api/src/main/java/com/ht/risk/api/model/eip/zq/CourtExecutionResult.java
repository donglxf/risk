package com.ht.risk.api.model.eip.zq;

import com.ht.ussp.core.Result;
import com.ht.ussp.core.ReturnCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourtExecutionResult<T> implements Serializable {
    /**
     * 返回码
     */
    private String returnCode;

    /**
     * 返回码描述
     */
    private String codeDesc;

    /**
     * 详情
     */
    private String msg;

    private List<T> data;

    public CourtExecutionResult() {
        super();
    }


    /**
     * 构建成功报文
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CourtExecutionResult<T> buildSuccess(List<T> data) {
        CourtExecutionResult<T> result = new CourtExecutionResult<T>();
        result.returnCode(ReturnCodeEnum.SUCCESS.getReturnCode()).codeDesc(ReturnCodeEnum.SUCCESS.getCodeDesc()).data(data);
        return result;
    }

    public static Result buildSuccess() {
        Result result = new Result();
        result.returnCode(ReturnCodeEnum.SUCCESS.getReturnCode()).codeDesc(ReturnCodeEnum.SUCCESS.getCodeDesc());
        return result;
    }

    /**
     * 构建失败报文
     *
     * @param <T>
     * @return
     */
    public static <T> CourtExecutionResult<T> buildFail() {
        CourtExecutionResult<T> result = new CourtExecutionResult<T>();
        result.returnCode(ReturnCodeEnum.FAIL.getReturnCode()).codeDesc(ReturnCodeEnum.FAIL.getCodeDesc());
        return result;
    }

    /**
     * 构建失败报文
     *
     * @param <T>
     * @return
     */
    public static <T> CourtExecutionResult<T> buildFail(String codeDesc, String msg) {
        CourtExecutionResult<T> result = new CourtExecutionResult<T>();
        result.returnCode(ReturnCodeEnum.FAIL.getReturnCode()).codeDesc(ReturnCodeEnum.FAIL.getCodeDesc());
        result.codeDesc(codeDesc);
        result.msg(msg);
        return result;
    }

    public CourtExecutionResult<T> returnCode(String errorCode) {
        this.returnCode = errorCode;
        return this;
    }

    public CourtExecutionResult<T> codeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
        return this;
    }

    public CourtExecutionResult<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public CourtExecutionResult<T> data(List<T> data) {
        this.data = data;
        return this;
    }
}
