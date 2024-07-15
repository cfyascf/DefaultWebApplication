import { createBrowserRouter } from "react-router-dom";
import Navbar from "../components/Navbar";

const mainRoutes = createBrowserRouter([
    {
        path: '/',
        element: (
            <Navbar/>
        )
    }
])

export default mainRoutes