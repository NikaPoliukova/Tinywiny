
import {User} from "../model/User";
import axios from "axios";

class UserService {

    async getUsers(): Promise<Array<User>> {
        const response = await axios.get<Array<User>>('http://localhost:8080/api/v1/users');
        return response.data;
    }
    async getUser(userId : number): Promise<User> {
        const response = await axios.get<User>('http://localhost:8080/api/v1/users/' + userId);
        return response.data;
    }

    async saveUser(user: User): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/registration', user);
        }

    async updateUser(user: User): Promise<void> {
        await axios.put('http://localhost:8080/api/v1/users', user);
    }

    getToken = async (username: string, password: string) => {
        let response;
        try{
            response = await axios.post('http://localhost:8080/api/v1/auth/login',
                {username: username, password: password}, {withCredentials: true});
        }
        catch (e: unknown) {}
        return response?.status == 200;
    }
}
export default new UserService();