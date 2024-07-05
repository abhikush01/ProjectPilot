import { Route, Routes } from "react-router-dom";
import Home from "./Pages/Home/Home";
import Navbar from "./Pages/Navbar/Navbar";
import ProjectDetails from "./Pages/ProjectDetails/ProjectDetails";
import IssueDetails from "./Pages/IssueDetails/IssueDetails";
import Subscription from "./Pages/Subscription/Subscription";
import Auth from "./Pages/AuthPage/Auth";

export default function App() {
  return (
    <>
      {false ? (
        <div>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/project/:id" element={<ProjectDetails />} />
            <Route
              path="/project/:id/issue/:issueId"
              element={<IssueDetails />}
            />
            <Route path="/upgrade" element={<Subscription />} />
          </Routes>
        </div>
      ) : (
        <Auth />
      )}
    </>
  );
}
