import api from "@/config/api";
import {
  ACCEPT_INVITATION_REQUEST,
  ACCEPT_INVITATION_SUCCESS,
  CREATE_PROJECT_REQUEST,
  CREATE_PROJECT_SUCCESS,
  DELETE_PROJECT_REQUEST,
  DELETE_PROJECT_SUCCESS,
  FETCH_PROJECT_BY_ID_REQUEST,
  FETCH_PROJECT_BY_ID_SUCCESS,
  FETCH_PROJECT_REQUEST,
  FETCH_PROJECT_SUCCESS,
  INVITE_TO_PROJECT_REQUEST,
  INVITE_TO_PROJECT_SUCCESS,
  SEARCH_PROJECT_REQUEST,
  SEARCH_PROJECT_SUCCESS,
} from "./ActionType";

export const fetchProjects =
  ({ category, tag }) =>
  async (dispatch) => {
    dispatch({ type: FETCH_PROJECT_REQUEST });
    try {
      const { data } = await api.get("/api/projects", {
        params: { category, tag },
      });

      dispatch({ type: FETCH_PROJECT_SUCCESS, projects: data });
    } catch (e) {
      console.log(e);
    }
  };

export const searchProjects = (keyword) => async (dispatch) => {
  dispatch({ type: SEARCH_PROJECT_REQUEST });
  try {
    const { data } = await api.get("/api/projects/search?keyword=" + keyword);

    dispatch({ type: SEARCH_PROJECT_SUCCESS, projects: data });
  } catch (e) {
    console.log(e);
  }
};

export const createProject = (projectData) => async (dispatch) => {
  dispatch({ type: CREATE_PROJECT_REQUEST });
  try {
    const { data } = await api.post("/api/projects", projectData);

    dispatch({ type: CREATE_PROJECT_SUCCESS, project: data });
  } catch (e) {
    console.log(e);
  }
};

export const fetchProjectByID = (id) => async (dispatch) => {
  dispatch({ type: FETCH_PROJECT_BY_ID_REQUEST });

  try {
    const { data } = await api.get("/api/projects/" + id);

    dispatch({ type: FETCH_PROJECT_BY_ID_SUCCESS, project: data });
  } catch (e) {
    console.log(e);
  }
};

export const deleteProject =
  ({ projectId }) =>
  async (dispatch) => {
    dispatch({ type: DELETE_PROJECT_REQUEST });
    try {
      await api.delete("/api/projects/" + projectId);

      dispatch({ type: DELETE_PROJECT_SUCCESS, projectId });
    } catch (e) {
      console.log(e);
    }
  };

export const inviteToProject =
  ({ email, projectId }) =>
  async (dispatch) => {
    dispatch({ type: INVITE_TO_PROJECT_REQUEST });
    try {
      const { data } = await api.post("/api/projects/invite", {
        email,
        projectId,
      });

      dispatch({ type: INVITE_TO_PROJECT_SUCCESS, project: data });
    } catch (e) {
      console.log(e);
    }
  };

export const acceptProject =
  ({ token, navigate }) =>
  async (dispatch) => {
    dispatch({ type: ACCEPT_INVITATION_REQUEST });
    try {
      const { data } = await api.get("/api/projects/accept_invite", {
        params: {
          token,
        },
      });

      dispatch({ type: ACCEPT_INVITATION_SUCCESS, project: data });
      navigate("/project/" + data.projectId);
    } catch (e) {
      console.log(e);
    }
  };
