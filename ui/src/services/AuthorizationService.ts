import axios from 'axios';
import {User} from "../model/User";

class AuthorizationService {
   /* async login(userName: string, password: string) : Promise<string>{
        const response = await axios.post<string>(            'http://localhost:8080/login',
            {userName: userName, password: password},
            {withCredentials: true}
        ).catch(function (error) {
            return error.response.status;
        })
        return response.status;
    }*/


    // Make a request to the Java backend to obtain the JWT token
    async login(userName: string, password: string) : Promise<string>{
        axios.post('http://localhost:8080/login', { userName, password })
            .then(response => {
                const { token } = response.data;
            }
        // Store the JWT token in the React application's state or in a cookie
        this.setState({ token });

                // Include the JWT token in the Authorization header of subsequent requests
                axios.get('/protected-resource', {
                    headers: { Authorization: Bearer ${token} }
                }).then(response => {
                    // Handle the response from the protected resource
                });
            })
            .catch(error => {
                // Handle login errors
            }); '''
}



export default new AuthorizationService();

// class AuthorizationService {
//     async login(userName: string, password: string): Promise<number> {
//         const response = await axios.post('http://localhost:8080/api/v1/login',
//             {userName: userName, password: password});
//         return response.status;
//     }
//
//     async getUser(userName: string, password: string): Promise<User> {
//         const param = {userName: userName, password: password}
//         const response = await axios.post('http://localhost:8080/api/v1/users/user', {
//                 data: param
//             }
//         );
//         return response.data;
//     }
// }
//
// export default new AuthorizationService();