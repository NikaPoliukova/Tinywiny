import axios from 'axios';

class AuthorizationService {
    async login(userName: string, password: string) {
        const response = await axios.post('http://localhost:8080/api/v1/login',
            {userName: userName, password: password}).catch(function (error) {
            return error.response.status;
        })
        return response.status;
    }
}

export default new AuthorizationService();