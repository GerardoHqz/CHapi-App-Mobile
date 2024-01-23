import Login from '../pages/Login/Login';
import Users from '../pages/Home/User/Users';
import Medicine from '../pages/Home/Medicine/Medicine';
import Hospital from '../pages/Home/Hospital/Hospital';
import NewMedicine from '../pages/Home/Medicine/NewMedicine'
import NewUser from '../pages/Home/User/NewUser'
import NewHospital from '../pages/Home/Hospital/NewHospital'

import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate replace to={"/login"} />} />
                <Route path="/login" element={<Login />} />
                <Route path="/users" element={<Users />} />
                <Route path="/newuser" element={<NewUser />} />
                <Route path="/medicine" element={<Medicine />} />
                <Route path="/newmedicine" element={<NewMedicine />} />
                <Route path="/hospital" element={<Hospital />} />
                <Route path="/newhospital" element={<NewHospital />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
