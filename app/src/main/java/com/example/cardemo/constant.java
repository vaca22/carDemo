package com.example.cardemo;

public interface constant {

    //Android car native definition
    interface VehiclePropertyGroup {
        int SYSTEM = 0x10000000;
        int VENDOR = 0x20000000;
        int MASK = 0xf0000000;
    }

    interface VehiclePropertyType {
        int STRING = 0x00100000;
        int BOOLEAN = 0x00200000;
        int INT32 = 0x00400000;
        int INT32_VEC = 0x00410000;
        int INT64 = 0x00500000;
        int INT64_VEC = 0x00510000;
        int FLOAT = 0x00600000;
        int FLOAT_VEC = 0x00610000;
        int BYTES = 0x00700000;
        int COMPLEX = 0x00e00000;
        int MIXED = 0x00e00000;
        int MASK = 0x00ff0000;
    }

    interface VehicleArea {
        int GLOBAL = 0x01000000;
        int ZONE = 0x02000000;
        int WINDOW = 0x03000000;
        int MIRROR = 0x04000000;
        int SEAT = 0x05000000;
        int DOOR = 0x06000000;
        int WHEEL = 0x07000000;
        int MASK = 0x0f000000;
    }


    //方向盘转角
    int AVM_EPS_STEERINGANGLE = 0x0473;

    //车速
    int VEHICLE_SPEED = 0x0207;

    //车速有效性
    int CLUSTER_BCS_VEHSPEEDVD=0x0952;

    //制动踏板深度
    int SETTINGS_VCU_BRKPEDPST = 0x0739;

    //制动踏板深度有效值
    int SETTINGS_VCU_BRKPEDPSTVD = 0x0738;

    //加速踏板深度
    int EMS_GAS_PEDAL_ACT_PST = 0x0208;

    //横向加速度
    int EMS_FIL_VEH_LONG_ACCEL = 0x0202;

    //纵向加速度
    int EMS_FIL_VEH_LAL_ACCEL = 0x0201;

    //航偏角
    int AVM_SAS_STEERING_ANGLE = 0x0472;

    //右后中间雷达与障碍物距离
    int CLUSTER_PAS_RRMidDistance=0x09B6;

    //左后中间雷达与障碍物距离
    int CLUSTER_PAS_RLMidDistance=0x09B7;

    //右后雷达与障碍物距离
    int CLUSTER_PAS_RRDistance=0x09B8;

    //左后雷达与障碍物距离
    int CLUSTER_PAS_RLDistance=0x09B9;

    //左后侧面雷达与障碍物距离
    int CLUSTER_PAS_PAS_RSLSideDistance=0x09BA;

    //右后侧面雷达与障碍物距离
    int CLUSTER_PAS_PAS_RSRSideDistance=0x09BB;

    //右前雷达与障碍物距离
    int CLUSTER_PAS_PAS_FRDistance=0x09BC;

    //左前雷达与障碍物距离
    int CLUSTER_PAS_PAS_FLDistance=0x09BD;

    //右前中间雷达与障碍物距离
    int CLUSTER_PAS_FRMidDistance=0x09BE;

    //左前中间雷达与障碍物距离
    int CLUSTER_PAS_FLMidDistance=0x09BF;

    //右前侧面雷达与障碍物距离
    int CLUSTER_PAS_FSRSideDistance=0x09C0;

    //左前侧面雷达与障碍物距离
    int CLUSTER_PAS_FSLSideDistance=0x09C1;

    //雷达工作模式
    int CLUSTER_PAS_MODE =0x09AD;

    //转向灯开关输入
    int AVM_UINM_TURN_LIGHT_SW_ST = 0x02A9;

    //方向盘转向角速度
    int EPS_STEERING_ANGLE_SPD = 0x04D5;

    //制动踏板状态
    int EMS_BRKPEDAL_ST = 0x04D6;

    //与目标车前车距离
    int FRONT_TRACK_TARGET_LONGPOS = 0x04D7;

    //车道偏离
    int LDW_ACTIVE_DIRECTION = 0x04D8;

    //左转向灯(输出状态)
    int FL_TURNL_LIGHT_ST = 0x04D9;

    //右转向灯
    int FR_TURNL_LIGHT_ST = 0x04DA;

    //ECG信号
    int ECG_VALUE = 0x04DB;

    //ECG Lead Off status
    int ECG_LEAD_OFFST = 0x04DC;

    //ECG INP Lead Off status
    int ECG_INP_LEAD_OFFST = 0x04DD;

    //ECG INN Lead Off status
    int ECG_INN_LEAD_OFFST = 0x04DE;

    //ECG message counter
    int ECG_MSG_COUNTER = 0x04DF;

}
