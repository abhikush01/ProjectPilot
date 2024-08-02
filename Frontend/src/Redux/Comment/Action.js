import api from "@/config/api";
import * as actionTypes from "./ActionTypes";

export const createComment = (commentData) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.CREATE_COMMENT_REQUEST });
    try {
      const { data } = await api.post("api/comments", commentData);
      dispatch({ type: actionTypes.CREATE_COMMENT_SUCCESS, comment: data });
    } catch (e) {
      console.log(e);
    }
  };
};

export const deleteComment = (commentId) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.DELETE_COMMENT_REQUEST });
    try {
      await api.delete(`api/comments/${commentId}`);
      dispatch({ type: actionTypes.DELETE_COMMENT_SUCCESS, commentId });
    } catch (e) {
      console.log(e);
    }
  };
};

export const fetchComments = (issueId) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_COMMENTS_REQUEST });
    try {
      const { data } = await api.get(`api/comments/${issueId}`);
      dispatch({ type: actionTypes.FETCH_COMMENTS_SUCCESS, comments: data });
    } catch (e) {
      console.log(e);
    }
  };
};
