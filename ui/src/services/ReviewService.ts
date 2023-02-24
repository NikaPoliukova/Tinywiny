import axios from "axios";
import {Review} from "../model/Review";

class ReviewService {

    async getReviews(): Promise<Array<Review>> {
        const response = await axios.get<Array<Review>>('http://localhost:8080/api/v1/reviews');
        return response.data;
    }

    async saveReview(review: Review): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/reviews', review);
    }
    async deleteReview(id: number): Promise<void> {
        await axios.delete('http://localhost:8080/api/v1/reviews/'+id);
    }


}
export default new ReviewService();