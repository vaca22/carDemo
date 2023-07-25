package com.example.cardemo;


import static com.example.cardemo.constant.*;


public class propertyID {
    //方向盘转角
    public static final int ID_AVM_EPS_STEERINGANGLE =
            AVM_EPS_STEERINGANGLE
            | constant.VehiclePropertyGroup.VENDOR
            | constant.VehiclePropertyType.FLOAT
            | constant.VehicleArea.GLOBAL;

    //车速
    public static final int ID_VEHICLE_SPEED =
            VEHICLE_SPEED
            |VehiclePropertyGroup.VENDOR
            |VehiclePropertyType.FLOAT
            |VehicleArea.GLOBAL;

    //车速有效性
    public static final int ID_CLUSTER_BCS_VEHSPEEDVD=
            CLUSTER_BCS_VEHSPEEDVD
            |VehiclePropertyGroup.VENDOR
            |VehiclePropertyType.INT32
            |VehicleArea.GLOBAL;

    //制动踏板深度
    public static final int ID_SETTINGS_VCU_BRKPEDPST =
            SETTINGS_VCU_BRKPEDPST
            |VehiclePropertyGroup.VENDOR
            |VehiclePropertyType.FLOAT
            |VehicleArea.GLOBAL;

    //制动踏板深度有效值
    public static final int ID_SETTINGS_VCU_BRKPEDPSTVD =
            SETTINGS_VCU_BRKPEDPSTVD
            |VehiclePropertyGroup.VENDOR
            |VehiclePropertyType.INT32
            |VehicleArea.GLOBAL;

    //加速踏板深度
    public static final int ID_EMS_GAS_PEDAL_ACT_PST =
            EMS_GAS_PEDAL_ACT_PST
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.FLOAT
                    | VehicleArea.GLOBAL;

    //横向加速度
    public static final int ID_EMS_FIL_VEH_LONG_ACCEL =
            EMS_FIL_VEH_LONG_ACCEL
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.FLOAT
                    | VehicleArea.GLOBAL;

    //纵向加速度
    public static final int ID_EMS_FIL_VEH_LAL_ACCEL =
            EMS_FIL_VEH_LAL_ACCEL
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.FLOAT
                    | VehicleArea.GLOBAL;

    //航偏角
    public static final int ID_AVM_SAS_STEERING_ANGLE =
            AVM_SAS_STEERING_ANGLE
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.FLOAT
                    | VehicleArea.GLOBAL;

    //右后中间雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_RRMidDistance=
            CLUSTER_PAS_RRMidDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左后中间雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_RLMidDistance=
            CLUSTER_PAS_RLMidDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //右后雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_RRDistance=
            CLUSTER_PAS_RRDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左后雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_RLDistance=
            CLUSTER_PAS_RLDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左后侧面雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_PAS_RSLSideDistance=
            CLUSTER_PAS_PAS_RSLSideDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //右后侧面雷达与障碍物距离
    public static final int  ID_CLUSTER_PAS_PAS_RSRSideDistance=
            CLUSTER_PAS_PAS_RSRSideDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //右前雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_PAS_FRDistance=
            CLUSTER_PAS_PAS_FRDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左前雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_PAS_FLDistance=
            CLUSTER_PAS_PAS_FLDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //右前中间雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_FRMidDistance=
            CLUSTER_PAS_FRMidDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左前中间雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_FLMidDistance=
            CLUSTER_PAS_FLMidDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //右前侧面雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_FSRSideDistance=
            CLUSTER_PAS_FSRSideDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左前侧面雷达与障碍物距离
    public static final int ID_CLUSTER_PAS_FSLSideDistance=
            CLUSTER_PAS_FSLSideDistance
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //雷达工作模式
    public static final int ID_CLUSTER_PAS_MODE =
            CLUSTER_PAS_MODE
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //转向灯开关输入
    public static final int ID_AVM_UINM_TURN_LIGHT_SW_ST =
            AVM_UINM_TURN_LIGHT_SW_ST
                    | VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //方向盘转向角速度
    public static final int ID_EPS_STEERING_ANGLE_SPD =
            EPS_STEERING_ANGLE_SPD
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //制动踏板状态
    public static final int ID_EMS_BRKPEDAL_ST =
            EMS_BRKPEDAL_ST
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //与目标车前车距离
    public static final int ID_FRONT_TRACK_TARGET_LONGPOS =
            FRONT_TRACK_TARGET_LONGPOS
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.FLOAT
                    | VehicleArea.GLOBAL;

    //车道偏离
    public static final int ID_LDW_ACTIVE_DIRECTION =
            LDW_ACTIVE_DIRECTION
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //左转向灯(输出状态)
    public static final int ID_FL_TURNL_LIGHT_ST =
            FL_TURNL_LIGHT_ST
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //右转向灯
    public static final int ID_FR_TURNL_LIGHT_ST =
            FR_TURNL_LIGHT_ST
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //ECG信号
    public static final int ID_ECG_VALUE =
            ECG_VALUE
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32_VEC
                    | VehicleArea.GLOBAL;

    //ECG Lead Off status
    public static final int ID_ECG_LEAD_OFFST =
            ECG_LEAD_OFFST
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //ECG INP Lead Off status
    public static final int ID_ECG_INP_LEAD_OFFST =
            ECG_INP_LEAD_OFFST
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //ECG INN Lead Off status
    public static final int ID_ECG_INN_LEAD_OFFST =
            ECG_INN_LEAD_OFFST
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;

    //ECG message counter
    public static final int ID_ECG_MSG_COUNTER =
            ECG_MSG_COUNTER
                    |VehiclePropertyGroup.VENDOR
                    | VehiclePropertyType.INT32
                    | VehicleArea.GLOBAL;


    public static Integer[] mControlIds=new Integer[]{
            ID_AVM_EPS_STEERINGANGLE,
            ID_VEHICLE_SPEED,
            ID_CLUSTER_BCS_VEHSPEEDVD,
            ID_SETTINGS_VCU_BRKPEDPST,
            ID_SETTINGS_VCU_BRKPEDPSTVD,
            ID_EMS_GAS_PEDAL_ACT_PST,
            ID_EMS_FIL_VEH_LONG_ACCEL,
            ID_EMS_FIL_VEH_LAL_ACCEL,
            ID_AVM_SAS_STEERING_ANGLE,
            ID_CLUSTER_PAS_RRMidDistance,
            ID_CLUSTER_PAS_RLMidDistance,
            ID_CLUSTER_PAS_RRDistance,
            ID_CLUSTER_PAS_RLDistance,
            ID_CLUSTER_PAS_PAS_RSLSideDistance,
            ID_CLUSTER_PAS_PAS_RSRSideDistance,
            ID_CLUSTER_PAS_PAS_FRDistance,
            ID_CLUSTER_PAS_PAS_FLDistance,
            ID_CLUSTER_PAS_FRMidDistance,
            ID_CLUSTER_PAS_FLMidDistance,
            ID_CLUSTER_PAS_FSRSideDistance,
            ID_CLUSTER_PAS_FSLSideDistance,
            ID_CLUSTER_PAS_MODE,
            ID_AVM_UINM_TURN_LIGHT_SW_ST,
            ID_EPS_STEERING_ANGLE_SPD,
            ID_EMS_BRKPEDAL_ST,
            ID_FRONT_TRACK_TARGET_LONGPOS,
            ID_LDW_ACTIVE_DIRECTION,
            ID_FL_TURNL_LIGHT_ST,
            ID_FR_TURNL_LIGHT_ST,
            ID_ECG_VALUE,
            ID_ECG_LEAD_OFFST,
            ID_ECG_INP_LEAD_OFFST,
            ID_ECG_INN_LEAD_OFFST,
            ID_ECG_MSG_COUNTER
    };


    //create a list of above names strings
    public static String[] mControlNames=new String[]{
            "AVM_EPS_STEERINGANGLE",
            "VEHICLE_SPEED",
            "CLUSTER_BCS_VEHSPEEDVD",
            "SETTINGS_VCU_BRKPEDPST",
            "SETTINGS_VCU_BRKPEDPSTVD",
            "EMS_GAS_PEDAL_ACT_PST",
            "EMS_FIL_VEH_LONG_ACCEL",
            "EMS_FIL_VEH_LAL_ACCEL",
            "AVM_SAS_STEERING_ANGLE",
            "CLUSTER_PAS_RRMidDistance",
            "CLUSTER_PAS_RLMidDistance",
            "CLUSTER_PAS_RRDistance",
            "CLUSTER_PAS_RLDistance",
            "CLUSTER_PAS_PAS_RSLSideDistance",
            "CLUSTER_PAS_PAS_RSRSideDistance",
            "CLUSTER_PAS_PAS_FRDistance",
            "CLUSTER_PAS_PAS_FLDistance",
            "CLUSTER_PAS_FRMidDistance",
            "CLUSTER_PAS_FLMidDistance",
            "CLUSTER_PAS_FSRSideDistance",
            "CLUSTER_PAS_FSLSideDistance",
            "CLUSTER_PAS_MODE",
            "AVM_UINM_TURN_LIGHT_SW_ST",
            "EPS_STEERING_ANGLE_SPD",
            "EMS_BRKPEDAL_ST",
            "FRONT_TRACK_TARGET_LONGPOS",
            "LDW_ACTIVE_DIRECTION",
            "FL_TURNL_LIGHT_ST",
            "FR_TURNL_LIGHT_ST",
            "ECG_VALUE",
            "ECG_LEAD_OFFST",
            "ECG_INP_LEAD_OFFST",
            "ECG_INN_LEAD_OFFST",
            "ECG_MSG_COUNTER"
    };


}
