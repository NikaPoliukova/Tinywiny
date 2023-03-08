import {User} from "../model/User";
import axios from "axios";

class UserService {

    async getUsers(): Promise<Array<User>> {
        const response = await axios.get<Array<User>>('http://localhost:8080/api/v1/users');
        return response.data;
    }

    async getUser(userId: number): Promise<User> {
        const response = await axios.get<User>('http://localhost:8080/api/v1/users/' + userId);
        return response.data;
    }

    async saveUser(user: User) {
        await axios.post('http://localhost:8080/api/v1/registration', user);
    }

    async updateUser(user: User): Promise<void> {
        await axios.put('http://localhost:8080/api/v1/users', user);
    }
}

export default new UserService();