import axios from 'axios';

class AuthorizationService {
    async login(userName: string, password: string) {
        const response = await axios.post<string>('http://localhost:8080/api/v1/login',
            {userName: userName, password: password},
            {withCredentials: true}
        ).catch(function (error) {
            return error.response.status;
        })
        return response.status;
    }

    async logout() {
        const response = await axios.get('http://localhost:8080/api/v1/logout', {withCredentials: true})
        return response.status;
    }
}

export default new AuthorizationService();
