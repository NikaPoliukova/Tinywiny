import {User} from "../model/User";
import axios from "axios";

class SessionService {
    async getSession(): Promise<User> {
        const response = await axios.get<User>(
            'http://localhost:8080/api/v1/sessions',
            { withCredentials: true }
        );
        return response.data;
    }
}

export default new SessionService();


// class SessionService {
//
//     getToken = async (userName: string, password: string) => {
//         let response;
//         try {
//             response = await axios.post('http://localhost:8080/api/v1/login',
//                 {userName: userName, password: password}, {withCredentials: true});
//         } catch (e: unknown) {
//         }
//         return response?.status == 200;
//     }
//      async getSession(): Promise<User> {
//         const response = await axios.get<User>(
//             'http://localhost:8080/api/v1/users/me',
//             { withCredentials: true }
//         );
//         return response.data;
//     }
// }
// export default new SessionService();