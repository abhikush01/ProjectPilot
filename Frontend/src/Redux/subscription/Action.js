import api from "@/config/api";
import * as actionType from "./ActionType";

export const getUserSubscription = () => {
  return async (dispatch) => {
    dispatch({ type: actionType.GET_USER_SUBSCRIPTION_REQUEST });
    try {
      const { data } = await api.get("/api/subscriptions/user", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("jwt")}`,
        },
      });
      dispatch({
        type: actionType.GET_USER_SUBSCRIPTION_SUCCESS,
        payload: data,
      });
    } catch (e) {
      console.log(e);
    }
  };
};

export const upgradeSubscription = ({ planType }) => {
  return async (dispatch) => {
    dispatch({ type: actionType.UPGRADE_SUBSCRIPTION_REQUEST });
    try {
      const { data } = await api.patch("/api/subscriptions/upgrade", null, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("jwt")}`,
        },
        params: {
          planType: planType,
        },
      });
      dispatch({
        type: actionType.UPGRADE_SUBSCRIPTION_SUCCESS,
        payload: data,
      });
      console.log("upgrade subscription", data);
    } catch (e) {
      console.log(e);
    }
  };
};
