import api from "@/config/api";
import * as actionTypes from "./ActionType";

export const sendMessage = (messageData) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.SEND_MESSAGE_REQUEST });
    try {
      const { data } = await api.post("/api/messages/send", messageData);
      dispatch({
        type: actionTypes.SEND_MESSAGE_SUCCESS,
        message: data,
      });
    } catch (e) {
      console.log(e);
    }
  };
};

export const fetchChatByProjectID = (projectId) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_CHAT_BY_PROJECT_REQUEST });
    try {
      const { data } = await api.get(`/api/projects/${projectId}/chat`);
      dispatch({
        type: actionTypes.FETCH_CHAT_BY_PROJECT_SUCCESS,
        chat: data,
      });
      console.log(`Chat Data`, data);
    } catch (e) {
      console.log(e);
    }
  };
};

export const fetchChatMessages = (chatId) => {
  console.log("fetchChat", chatId);
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_CHAT_MESSAGES_REQUEST });
    try {
      const { data } = await api.get(`/api/messages/chat/${chatId}`);
      dispatch({
        type: actionTypes.FETCH_CHAT_MESSAGES_SUCCESS,
        chatId,
        messages: data,
      });
      console.log("Messages", data);
    } catch (e) {
      console.log(e);
    }
  };
};
