import axios from 'axios';

class AuthorizationService {
    async login(login: string, password: string) {

        const response = await axios.post(
            'http://localhost:8080/api/v1/login',
            { userName: login, password: password }
                  ).catch(function (error) {
            return error.response.status;
        })
        return response.status;
    }
}
export default new AuthorizationService();