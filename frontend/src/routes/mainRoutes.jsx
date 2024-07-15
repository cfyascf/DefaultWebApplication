import { createBrowserRouter } from "react-router-dom";

const mainRoutes = createBrowserRouter([
    {
        path: '/',
        element: (
            <div>
                <h1>hello world!</h1>
            </div>
        )
    }
])

export default mainRoutes